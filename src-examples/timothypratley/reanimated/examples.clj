(ns timothypratley.reanimated.examples
  (:require
   [devcards.core :refer [defcard-rg]]
   ;;[clojure.tools.reader :as reader]
   ))

(defmacro example [name desc form]
  (let [fname# (symbol (str name "-component"))]
    `(do (defn ~fname# [] ~form)
         (defcard-rg ~(symbol (str name "-example"))
           ~(str desc \newline \newline
                 "```Clojure" \newline
                 ;; TODO: pprint isn't perfect for code
                 (with-out-str (clojure.pprint/pprint form)) \newline
                 "```")
           [~fname#]))))

;; TODO: use a textbox instead so user can edit
(defmacro example2 [name desc form]
  (let [fname# (symbol (str name "-component"))]
    `(do (defn ~fname# []
           ~form)
         (defcard-rg ~(symbol (str name "-example"))
           ~(str desc \newline \newline
                 "```Clojure" \newline
                 ;; TODO: pprint isn't perfect for code
                 (with-out-str (clojure.pprint/pprint form)) \newline
                 "```")
           [:div
            [:p ~desc]
            [:textarea
             {:style {:width "100%"}
              :default-value ~(with-out-str (clojure.pprint/pprint form))}]
            [:button
             #_{:on-click
              #(reader/read-string "[1 2 3]")}
             "go"]
            [:br]
            [~fname#]]))))
