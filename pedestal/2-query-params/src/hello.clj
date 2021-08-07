(ns hello
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(def unmentionables
  #{"YHWH" "Voldemort" "Mxyzptlk" "Rumplestiltskin" "曹操" "Saci"})

(defn ok [body]
  (when-not (nil? body)
    {:status 200 :body body}))

(defn bad-request [body]
  {:status 400 :body body})

(defn not-found []
  {:status 404 :body "Not Found\n"})

(defn greeting-for [nm]
  (cond
    (unmentionables nm) nil
    (empty? nm)         "Hello, world!\n"
    :else               (str "Hello, " nm "\n")))

(defn respond-hello [request]
  (let [nm   (get-in request [:query-params :name])
        resp (if (not= "" nm)
               (ok (greeting-for nm))
               (bad-request "Zero-length name used as parameter\n"))]
    (or resp (not-found))))

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]}))

(def service-map
  {::http/routes routes
   ::http/type   :jetty
   ::http/port   8890})

(defn start []
  (http/start (http/create-server service-map)))

(defonce server (atom nil))

(defn start-dev []
  (reset! server
          (http/start (http/create-server
                       (assoc service-map
                              ::http/join? false)))))

(defn stop-dev []
  (http/stop @server))

(defn restart []
  (stop-dev)
  (start-dev))
