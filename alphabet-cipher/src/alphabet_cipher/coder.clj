(ns alphabet-cipher.coder)

(defn repeat-keyword [keyword message]
  (let [keyword-count (count keyword)
        message-count (count message)]
    (if (>= keyword-count message-count)
      (subs keyword 0 message-count)
      (recur (str keyword keyword) message))))

(defn is-at-least-one-empty? [string-list-1 string-list-2]
  (or
   (empty? string-list-1)
   (empty? string-list-2)))

(defn do-other-thing [value]
  (if (< value 25)
    (+ value 97)
    value))

(defn do-something [letter-1 letter-2]
  (char
   (do-other-thing
    (mod (+ (mod (int letter-2) 97) (int letter-1)) 123))))

(defn loop-through-two-strings [string-1 string-2]
  (loop [string-list-1 (seq string-1)
         string-list-2 (seq string-2)
         result        ""]
    (if (is-at-least-one-empty? string-list-1 string-list-2)
      result
      (do
        (let [letter-1 (first string-list-1)
              letter-2 (first string-list-2)
              generated-letter (do-something letter-1 letter-2)]
          (recur
           (rest string-list-1)
           (rest string-list-2)
           (str result generated-letter)))))))

(defn encode [keyword message]
  (-> keyword
      (repeat-keyword message)
      (loop-through-two-strings message)))

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")
