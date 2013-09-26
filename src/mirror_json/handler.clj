(ns mirror-json.handler
  (:use mirror-json.cors)
  (:use compojure.core)
  (:use ring.util.response)
  (:use ring.adapter.jetty)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]))

;; JSON Responses / Statuses
(defn empty-response []
  {:body {}})

(defn mirror-response [body]
  {:body body})

(defn delete-response []
  {:status 204
   :body body})

;; App Routes, basically an ANY but easier to debug
(defroutes app-routes
  (GET     "/*" []           (empty-response))
  (POST    "/*" {body :body} (mirror-response body))
  (PUT     "/*" {body :body} (mirror-response body))
  (DELETE  "/*" {body :body} (delete-response body)))

;; Create the App here
(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-response)
      (wrap-cors)))
