(ns app.P01-IncDec
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]))

; First demonstration of client/server transfer:
; a full stack function with both frontend and backend parts,
; all defined in the same expression


(e/defn IncDecApp []
        (e/client
          (let [!push (atom ())]
            (dom/input (dom/props {:placeholder "lutfen bir sayı giriniz..."})
                       (dom/on "keydown" (e/fn [enter]
                                               (when (= "Enter" (.-key enter))
                                                 (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                   ((reset! !push (parse-long givenValue)))
                                                   (set! (.-value dom/node)
                                                         (dom/props {:placeholder (str "Kullanicinin girdigi sayi= "givenValue)})))))))

            (if (>= 10 (e/watch !push)) (dom/h1 (dom/text (e/watch !push))
                                                (dom/props {:style {:color "white" :background-color "black"}}))
                                        (dom/h1 (dom/text (e/watch !push))
                                                (dom/props {:style {:color "red" :background-color "grey"}})))


            (ui/button (e/fn [] (swap! !push inc))
                       (dom/text "Increase!!!")
                       (dom/props {:class "Button" :style {:color "white" :background-color "black"}}))


            (ui/button (e/fn [] (swap! !push dec))
                       (dom/text "Decrese!!!")
                       (dom/props {:class "Button" :style {:color "white" :background-color "black"}}))
            )
          )
        )




