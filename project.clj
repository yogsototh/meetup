(defproject meetup "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [

                 ;; basic
                 [org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-devel "1.3.2"]
                 [ring/ring-jetty-adapter "1.3.1"]

                 ;;compojure
                 [compojure "1.3.1"]

                 ;;compojure-api
                 [metosin/compojure-api "0.16.5"]
                 [prismatic/schema "0.3.3"]
                 [metosin/ring-http-response "0.5.2"]
                 [metosin/ring-swagger "0.14.1"]
                 [metosin/ring-swagger-ui "2.0.17"]])
