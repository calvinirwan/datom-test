(ns datom-test.core
  (:gen-class)
  (:require [datomic.api :as d]
            [clojure.pprint :refer [pprint]]))

(def db "zen-eng")
(def transactor "localhost:4334/")
(defn uri [db-name] (str "datomic:free://" transactor db-name))

(defn create-db [db-name] (d/create-database (uri db-name)))
;;(create-db uri)
(defn conn [db-name] (d/connect (uri db-name)))

(defn get-like [name](d/q '[:find ?p :in $ ?n :where [?e :person/name ?n]
			[?e :person/like ?p]] (d/db (conn "test")) name))
#_(def tx-result
  (d/transact
   (conn "test")
   [[:db/add
     (d/tempid :db.part/user)
     :db/doc
     "hello world"]]))
(defn schema-add 
  [db schema]
  (d/transact (conn db) schema))

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
