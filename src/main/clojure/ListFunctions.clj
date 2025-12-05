(ns List Functions)

;; member
(defn member? [atm list]
    (some #(= atm %) list))

;; append
(defn append [list1 list2]
    (concat list1 list2))

;; map
(defn my-map [fun list]
    (map fun list))

;; same
(defn same? [list1 list2]
    (= list1 list2))

;; intersect
(defn intersect [list1 list2]
    (filter #(member? %list2) list1))