(ns mirror-json.handler
  (:use mirror-json.cors)
  (:use compojure.core)
  (:use ring.util.response)
  (:use ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

(defn empty-response []
  {:body {}})

(defn mirror-response [body]
  {:body body})

(defn delete-response []
  {:status 204})

(defroutes app-routes
  (GET     "/*" []           (empty-response))
  (POST    "/*" {body :body} (mirror-response body))
  (PUT     "/*" {body :body} (mirror-response body))
  (DELETE  "/*" {body :body} (delete-response body)))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-response)
      (wrap-cors)))
