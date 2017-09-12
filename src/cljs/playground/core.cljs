(ns playground.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [playground.events]
            [playground.subs]
            [playground.views :as views]
            [playground.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
