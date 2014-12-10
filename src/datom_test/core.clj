(ns datom-test.core
  (:gen-class)
  (:require [datomic.api :as d]
            [clojure.pprint :refer [pprint]]))

(def calvin "awesome")
(def uri "datomic:mem://awesome")

(defn create-db [uri] (d/create-database uri))

(def conn (d/connect uri))

(def tx-result
  (d/transact
   conn
   [[:db/add
     (d/tempid :db.part/user)
     :db/doc
     "hello world"]]))

