(ns playground.views
  (:require [re-frame.core :as re-frame]))


;; home

(defn home-panel []
  (let [contact-info (re-frame/subscribe [:contact-info])]
    (fn []
      (let [{:keys [first-name middle-name last-name roles address]} @contact-info]
        [:main.home
         [:div.vcard-wrapper
          [:div.vcard
           [:div.wrapper
            [:div.logo "79"]
            [:div.wrapper
             [:div.fn.n
              [:span.given-name first-name] " "
              [:span.additional-name middle-name] " "
              [:span.family-name last-name]]
             [:div.roles
              (into [:ul]
                    (map #(vector :li.role %) roles))]]]
           [:div.adr
            [:span.locality (:city address)] " / "
            [:span.region (:city address)] " / "
            [:span.country-name (:country address)]]
           [:div.geo (:lat address) " / " (:long address)]]]]))))


;; main
(defn main-panel []
  [home-panel])
