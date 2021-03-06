(ns datom-test.core
  (:gen-class)
  (:require [datomic.api :as d]
            [clojure.pprint :refer [pprint]]))

(def calvin "awesome")
(def uri "datomic:mem://awesome")

(defn create-db [uri] (d/create-database uri))
(create-db uri)
(def conn (d/connect uri))

(defn get-like [name](d/q '[:find ?p :in $ ?n :where [?e :person/name ?n]
			[?e :person/like ?p]] (d/db conn) name))
(def tx-result
  (d/transact
   conn
   [[:db/add
     (d/tempid :db.part/user)
     :db/doc
     "hello world"]]))

;;schema question
[{:db/id #db/id[:db.part/db]
  :db/ident :question/text
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db}
 {:db/id #db/id[:db.part/db]
  :db/ident :question/answer
  :db/valueType :db.type/ref
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db}
 ]

;;schema question answer
[{:db/id #db/id[:db.part/db]
  :db/ident :qa/text
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db}
 {:db/id #db/id[:db.part/db]
  :db/ident :question/answer
  :db/valueType :db.type/ref
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db}
 ]

;;schema answers
[{:db/id #db/id[:db.part/db]
  :db/ident :answer/text
  :db/valueType :db.type/string
  :db/cardinality :db.cardinality/one
  :db.install/_attribute :db.part/db}
 ]
