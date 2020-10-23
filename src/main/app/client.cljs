(ns app.client
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom :as dom]
    [fisher.plugin-support.dynamic-mount-support :as dms]
    [app.SPA :refer [SPA]]
    [fisher.plugins.editor1.editor1-root :as e1r]))


(defsc Root [this {:keys [dynamic] :as props}]
  {:query         [{:dynamic (comp/get-query dms/DynamicQueryTest)}]
   :initial-state {:dynamic {}}}
  #_(dom/div "TODO2"
      (e1r/ui-editor-1-root {:editor/ident [:editor/id 1]})
      (e1r/ui-editor-1-root {:editor/ident [:editor/id 4] :react-key 8}))

  (dom/div (dms/ui-dynamic-query-test dynamic)))

(defn ^:export init
  "Shadow-cljs sets this up to be our entry-point function. See shadow-cljs.edn `:init-fn` in the modules of the main build."
  []
  (app/mount! SPA Root "app")
  (js/console.log "Loaded"))

(defn ^:export refresh
  "During development, shadow-cljs will call this on every hot reload of source. See shadow-cljs.edn"
  []
  ;; re-mounting will cause forced UI refresh, update internals, etc.
  (app/mount! SPA Root "app")
  (js/console.log "Hot reload"))
