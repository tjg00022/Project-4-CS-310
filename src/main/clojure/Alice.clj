(ns Alice)

(defn third [list]
	(first (rest (rest list)))
)