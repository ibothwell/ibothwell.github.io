(ns playground.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :contact-info
 (fn [db]
   (:contact-info db)))
