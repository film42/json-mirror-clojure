(ns mirror-json.cors)

;; Define the CORS set, this will allow everything from everyone
(def cors-headers
  {"Access-Control-Allow-Headers" "Content-Type, Authorization"
   "Access-Control-Allow-Methods" "PUT, GET, POST, DELETE, OPTIONS"
   "Access-Control-Allow-Origin"  "*"})

;; Wrap-Cors inspired by ring-cors/wrap-cors
(defn wrap-cors [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc response :headers
        (merge (:headers response) cors-headers)))))
