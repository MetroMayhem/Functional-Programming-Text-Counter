(ns clojure-project.core)

;(defn readFile []
;(seq (char-array(slurp "C:\\Users\\alecs\\clojure-project\\resources\\WarAndPeace.txt"))))

;(defn readFile []
;  (char-array (slurp "C:\\Users\\alecs\\clojure-project\\resources\\WarAndPeace.txt")))

(defn readFile []
   (clojure.string/replace (clojure.string/lower-case (slurp ".\\resources\\WarAndPeace.txt")) #"[^a-z ]" ""))

(defn log [x] (/ (Math/log x) (Math/log 2)))

(defn dataStreamInfo [num_of_chars prob_of_chars]
  (* num_of_chars (- prob_of_chars) (log prob_of_chars)))



;(defn findTotalInformation [frequency_map probability_map]
;  (def information_count 0)
;  (doseq [[key val] (map vector (keys frequency_map) (keys probability_map))]
;    (def information_count (+ information_count (dataStreamInfo (key) (val)))))
;  information_count)

(defn findTotalInformation [frequency_map probability_map]
  (def information_count 0)
  (doseq [key (keys frequency_map)]
    (def information_count (+ information_count (dataStreamInfo (get frequency_map key) (get probability_map key)))))
  information_count)

(defn main []
 (def file_as_string (readFile))
 (def frequency_map (frequencies file_as_string))
 (def total_num_chars (count file_as_string))
 (println frequency_map total_num_chars)
 ;Make a new map and find probability of each letter to add as key/value pair to probability map
 (def probability_map (sorted-map))
 (doseq [[k v] (map vector (keys frequency_map) (vals frequency_map))]
   (def probability_map (assoc probability_map k (double (/ v total_num_chars)))))
 ;(doseq [f (keys frequencyMap)]
   ;(def newMap (assoc newMap f (double (/ (get frequencyMap f) total_num_chars)))))
  probability_map
  
  println(findTotalInformation frequency_map probability_map))

(main)