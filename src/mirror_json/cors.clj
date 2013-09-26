(ns mirror-json.cors)

(def cors-headers
  {"Access-Control-Allow-Headers" "Content-Type, Authorization"
   "Access-Control-Allow-Methods" "PUT, GET, POST, DELETE, OPTIONS"
   "Access-Control-Allow-Origin"  "*"})

(defn wrap-cors [handler]
  (fn [request]
    (let [response (handler request)]
      (assoc response :headers
        (merge (:headers response) cors-headers)))))
