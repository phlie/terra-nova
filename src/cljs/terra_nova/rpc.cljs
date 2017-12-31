(ns terra-nova.rpc
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [javelin.core]
   [castra.core :refer [mkremote]]))

(defc state nil)
(defc error nil)
(defc init-state nil)
(defc loading [])

(defc= random-number  (get state :random))
(defc= session-number (get state :session))
(defc= current-date   (get state :date))
(defc= current-money  (get state :money))
(defc= current-ship   (get state :ship))
(defc= number-of-employees (get state :staff))

(defc= greeting (get init-state :greet-msg))

(def get-state
  (mkremote 'terra-nova.api/get-state state error loading))

(def get-initial
  (mkremote 'terra-nova.api/get-initial init-state error loading))

(defn init []
  (get-state)
  (get-initial)
  (js/setInterval get-state 1000))
