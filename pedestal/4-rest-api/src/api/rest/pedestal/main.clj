(ns api.rest.pedestal.main
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [api.rest.pedestal.core :as core]))

(def echo
  {:name :echo
   :enter
   (fn [context]
     (let [request (:request context)
           response (core/ok context)]
       (assoc context :response response)))})

(defn transform-content
  [body content-type]
  (case content-type
    "text/html"        body
    "text/plain"       body
    "application/edn"  (pr-str body)
    "application/json" (json/write-str body)))

(defn coerce-to
  [response content-type]
  (-> response
      (update :body transform-content content-type)
      (assoc-in [:headers "Content-Type"] content-type)))

(def coerce-body
  {:name ::coerce-body
   :leave
   (fn [context]
     (cond-> context
       (nil? (get-in context [:response :headers "Content-Type"]))
       (update-in [:response] coerce-to (accepted-type context))))})

(def entity-render
  {:name :entity-render
   :leave
   (fn [context]
     (if-let [item (:result context)]
       (assoc context :response (ok item))
       context))})

(def routes
  (route/expand-routes
   #{["/todo"                    :post   [coerce-body model/db-interceptor list-create]]
     ["/todo"                    :get    [coerce-body echo] :route-name :list-query-form]
     ["/todo/:list-id"           :get    [coerce-body entity-render model/db-interceptor list-view]]
     ["/todo/:list-id"           :post   [coerce-body entity-render list-item-view model/db-interceptor list-item-create]]
     ["/todo/:list-id/:item-id"  :get    [coerce-body entity-render list-item-view model/db-interceptor]]
     ["/todo/:list-id/:item-id"  :put    [coerce-body echo] :route-name :list-item-update]
     ["/todo/:list-id/:item-id"  :delete [coerce-body echo] :route-name :list-item-delete]}))

(def service-map
  {::http/routes routes
   ::http/type   :jetty
   ::http/port   8890})

(defn start []
  (http/start (http/create-server core/service-map)))

(defonce server (atom nil))

(defn start-dev []
  (reset! server
          (http/start (http/create-server
                       (assoc core/service-map ::http/join? false)))))

(defn stop-dev []
  (http/stop @server))

(defn restart []
  (stop-dev)
  (start-dev))

(defn test-request [verb url]
  (io.pedestal.test/response-for (::http/service-fn @server) verb url))
