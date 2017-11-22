(ns status-im.test.utils.ethereum.tokens
  (:require [cljs.test :refer-macros [deftest is testing]]
            [status-im.utils.ethereum.tokens :as tokens]))

(deftest all
  (testing "Tokens must have icon paths"
    (doseq [[network tokens] tokens/all]
      (testing (str "For network " network)
        (doseq [token tokens]
          (is (not (nil? (get-in token [:icon :path])))))))))