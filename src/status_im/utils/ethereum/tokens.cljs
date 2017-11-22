(ns status-im.utils.ethereum.tokens
  (:require [clojure.string :as string]
            [status-im.ui.components.styles :as styles]
            [status-im.utils.ethereum.core :as core])
  (:require-macros [status-im.utils.ethereum.macros :refer [resolve-icons]]))

(defn- asset-border [color]
  {:border-color color :border-width 1 :border-radius 32})

(defn- safe-require [s]
  (try
    (js/require s)
    (catch :default _ nil)))

(defn- make-icon
  ([src] (make-icon src nil))
  ([src color]
   (when-let [source (safe-require (str "./resources/images/" src))]
     (merge {:source source}
            (when color {:style (asset-border color)})))))

(def default-icon (make-icon "assets/ethereum.png"))

(def eth-icon (make-icon "assets/ethereum.png" styles/color-gray-transparent-light))

(def all
  {:mainnet
   (resolve-icons
     [{:name     "Status Network Token"
       :symbol   :SNT
       :decimals 18
       :address  "0x744d70FDBE2Ba4CF95131626614a1763DF805B9E"}])
   :testnet
   (resolve-icons
     [{:name     "Status Test Token"
       :symbol   :STT
       :decimals 18
       :address  "0xc55cf4b03948d7ebc8b9e8bad92643703811d162"}])})

(defn token-for [chain-id symbol]
  (some #(if (= symbol (:symbol %)) %) (get all chain-id)))