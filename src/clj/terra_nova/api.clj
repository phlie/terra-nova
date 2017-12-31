(ns terra-nova.api
  (:require [castra.core :refer [defrpc *session*]]
            [clj-time.core :as t]))

(defn display-id
  []
  (str "ID: " (+ 1000000 (rand-int 9000000))))

(defn display-greeting
  []
  (let [temp (rand-int 3)]
    (cond
      (= temp 0) "Dear Citizen,"
      (= temp 1) "Welcome!"
      (= temp 2) "Corp TNI")))

(defn display-date
  []
  (let [star-citizen-date (t/plus (t/now) (t/years 930))]
    (str (t/year star-citizen-date) "-"
         (t/month star-citizen-date) "-"
         (t/day star-citizen-date) " "
         (t/hour star-citizen-date) ":"
         (t/minute star-citizen-date) ":"
         (t/second star-citizen-date))))


(def current-uec 54525)

(defn number-of-employees
  []
  "#Staff: 1")

(defn current-money
  []
  (def current-uec (+ (rand-int 10) current-uec))
  current-uec)

(defn current-ship
  []
  "F7C Hornet")

(defrpc get-state []
  (swap! *session* update-in [:id] #(or % (rand-int 100)))
  {:date (display-date)
   :session (:id @*session*)
   :money (current-money)
   :ship (current-ship)
   :staff (number-of-employees)})


(defrpc get-initial []
  {:greet-msg (display-greeting)})

