(ns bacedifo.views.welcome
  (:require [bacedifo.views.common :as common]
            [noir.response :as resp]
;            [noir.content.getting-started]
            )
  (:use [noir.core]
        [bacedifo.models.bacedifo :only [init-bacedifo bacedify-string]]
        ))


(defpage "/" []
  (common/layout)
  )

(defpage "/welcome" []
  (common/layout)
  )

(defpage [:get "/translate"] []
  (common/layout)
  )

(defpage [:post "/translate"] {:keys [text]}
  (init-bacedifo)
  (common/layout
   [:p#bacedifo (bacedify-string text)]
   )
  )

