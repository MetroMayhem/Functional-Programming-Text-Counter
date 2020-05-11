(ns clojure-project.core)

(require '[com.climate.claypoole :as cp])

(defn readFile []
   (clojure.string/replace (clojure.string/lower-case (slurp ".\\resources\\WarAndPeace.txt")) #"[^a-z ]" ""))

(defn log [x] (/ (Math/log x) (Math/log 2)))

(defn dataStreamInfo [num_of_chars prob_of_chars]
  (* num_of_chars (- prob_of_chars) (log prob_of_chars)))

(defn findTotalInformation [frequency_map probability_map]
  (def information_count 0)
  (cp/pfor 64 [key (keys frequency_map)]
    (def information_count (+ information_count (dataStreamInfo (get frequency_map key) (get probability_map key))))) 
  information_count)

(defn findProbabilityMap [frequency_map total_num_chars]
  (def probability_map (hash-map))
  (cp/pfor 64 [f (keys frequency_map)]
   (def probability_map (assoc probability_map f (double (/ (get frequency_map f) total_num_chars)))))
  probability_map)

(defn getNCharFreqs [inputString n]
  (frequencies (partition n inputString)))

(def file_as_string (readFile))

(defn main []
 (def total_num_chars (count file_as_string)) 
 
 ;Single character
 (def frequency_map (frequencies file_as_string))
 (def probability_map (findProbabilityMap frequency_map total_num_chars))
 (println (findTotalInformation frequency_map probability_map))
 
 ;Split characters into two
 (def pairs_frequency_map (getNCharFreqs file_as_string 2))
 (def pairs_probability_map (findProbabilityMap pairs_frequency_map total_num_chars))
 (println(findTotalInformation pairs_frequency_map pairs_probability_map))
 
 ;Split characters into three
 (def tri_frequency_map (getNCharFreqs file_as_string 3))
 (def tri_probability_map (findProbabilityMap tri_frequency_map total_num_chars))
 (println(findTotalInformation tri_frequency_map tri_probability_map))
 )
(time
(main)
)