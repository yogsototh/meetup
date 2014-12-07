(ns meetup.compojure
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]
            [clojure.walk :refer [keywordize-keys]]
            [compojure.core :refer [defroutes POST]]
            [compojure.route :as route]))

(defonce orders (atom {}))

(defn order-pizza-handler [params]
  (let [recipes #{:hawaian :italian :vegan}
        max-count 5
        p (keywordize-keys params)]

      (if ((keyword(:recipe p)) recipes)
        (if-not (> (read-string (:count p)) max-count)
          (do (swap! orders into {(keyword (str (java.util.UUID/randomUUID))) p})
            {:status 200
               :body "your pizza will be available shortly"})
          {:status 404
           :body "you ordered too many pizzas"})

        {:status 404
         :body "the requested recipe does not exists"})))

(defn cancel-pizza-handler [params]

  @orders

  (let [id (:id (keywordize-keys params))]
    (if-not (nil? (get @orders (keyword id)))
        (do (swap! orders dissoc (keyword id))
            {:status 200
             :body "your order has been canceled"})

        {:status 200
         :body "your order does not exists"})))

(defroutes handler
  (POST "/order/pizza" {params :params} (order-pizza-handler params))
  (POST "/cancel/pizza" {params :params} (cancel-pizza-handler params))

  (route/not-found "<h1>Invalid route.</h1>"))

(defonce server
  (run-jetty (-> #'handler
                 (wrap-stacktrace)
                 (wrap-params))
             {:port 3001 :join? false}))
