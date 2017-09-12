(defproject playground "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/core.async "0.3.443"]
                 [reagent "0.7.0"]
                 [re-frame "0.10.1"]
                 [day8.re-frame/async-flow-fx "0.0.8"]
                 [com.cognitect/transit-cljs "0.8.239"]
                 [cljsjs/react-with-addons "15.4.0-0"]
                 [cljsjs/react-dom "15.4.0-0"]
                 [cljsjs/react-dom-server "15.4.0-0"]
                 [secretary "1.2.3"]
                 [funcool/bide "1.5.0"]
                 [venantius/accountant "0.1.9"]
                 [garden "1.3.2"]
                 [cljs-ajax "0.5.8"]
                 [cljsjs/d3 "4.3.0-4"]
                 [cljsjs/d3-scale-chromatic "1.1.1-1"]
                 [prismatic/schema "1.1.3"]
                 [prismatic/schema-generators "0.1.0"]
                 [com.lucasbradstreet/cljs-uuid-utils "1.0.2"]]

  :exclusions [com.cognitect/transit-clj
               com.cognitect/transit-cljs
               cljsjs/react
               cljsjs/react-dom
               cljsjs/react-dom-server]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-less "1.7.5"]
            [lein-garden "0.2.8"]
            [lein-kibit "0.1.3"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :less {:source-paths ["less"]
         :target-path  "resources/public/css"}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[org.clojure/tools.nrepl "0.2.12"]
                   [binaryage/devtools "0.9.4"]
                   [figwheel "0.5.13"]
                   [figwheel-sidecar "0.5.13"]
                   [com.cemerick/piggieback "0.2.2"]
                   [devcards "0.2.3" :exclusions [cljsjs/react]]
                   [cljs-react-test "0.1.4-SNAPSHOT" :exclusions [cljsjs/react]]
                   [lein-doo "0.1.7"]
                   [alembic "0.3.2"]]
    :injections [(require 'pjstadig.humane-test-output)
                 (pjstadig.humane-test-output/activate!)]
    :plugins      [[lein-figwheel "0.5.13"]
                   [lein-doo "0.1.7"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "playground.core/mount-root"}
     :compiler     {:main                 playground.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            playground.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}


    ]}

  )
