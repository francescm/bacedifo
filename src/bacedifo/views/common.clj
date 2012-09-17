(ns bacedifo.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.form-helpers]
        [hiccup.page-helpers :only [include-css html5 link-to]]))

(defpartial translate-form[]
  (form-to [:post "/translate"]
            (text-field "text")
            (submit-button "translate"))
  )


(defpartial layout [& content]
            (html5
              [:head
               [:title "bacedifo"]
               (include-css "/css/reset.css")]
              [:body
               [:h1 "Translate to bacedifo"]
               [:div#wrapper
                content
               (translate-form) ]
               [:p#signature "Download source at " (link-to "http://github.com/" "github")]
               ]))
