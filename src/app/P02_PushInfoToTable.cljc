(ns app.P02-PushInfoToTable
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            ))


(e/defn TableApp []
        (e/client
          (let [!button (atom "")]
            (let [!userName (atom "")]
              (let [!email (atom "")]
                (let [!bg-color (atom "inherit")]
                  (let [!bg-color2 (atom "inherit")]
                    (dom/input (dom/props {:id 1 :placeholder "Please write your username..." :style {:background-color (e/watch !bg-color)}})
                               (dom/on "keydown" (e/fn [enter]
                                                       (when (= "Enter" (.-key enter))
                                                         (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                           (reset! !userName givenValue)
                                                           (set! (.-value dom/node)
                                                                 (dom/props {:placeholder (str "Given Username= " givenValue)}))))))
                               (dom/on "keyup" (e/fn [keyup]
                                                     (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                       (reset! !button givenValue)
                                                       )))
                               )
                    (ui/button (e/fn [] ((reset! !userName (e/watch !button)) (reset! !bg-color "blue")))
                               (dom/text "Submit!!!")
                               (dom/props {:class "Button" :style {:color "white" :background-color "black"}}))

                    (dom/input (dom/props {:placeholder "Please write your email..." :style {:background-color (e/watch !bg-color2)}})
                               (dom/on "keydown" (e/fn [enter]
                                                       (when (= "Enter" (.-key enter))
                                                         (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                           (reset! !email givenValue)
                                                           (set! (.-value dom/node)
                                                                 (dom/props {:placeholder (str "Given email= " givenValue)}))))))
                               (dom/on "keyup" (e/fn [keyup]
                                                     (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                       (reset! !button givenValue)
                                                       )))
                               )
                    (ui/button (e/fn [] ((reset! !email (e/watch !button)) (reset! !bg-color2 "red")))
                               (dom/text "Submit!!!")
                               (dom/props {:class "Button" :style {:color "white" :background-color "black"}}))

                    (dom/table (dom/props {:class "hyperfiddle"})
                               (e/client
                                 (dom/tr
                                   (dom/th (dom/text (str "Username")))
                                   (dom/th (dom/text (str "Email")))
                                   )
                                 (dom/tr
                                   (dom/td (dom/text (e/watch !userName)))
                                   (dom/td (dom/text (e/watch !email)))
                                   )
                                 )
                               )
                    )
                  )
                )
              )
            )
          )
        )

;(.-value dom/node) bize input olarak girdiğimiz değer neyse onu dönüyor.


;(let [!search (atom ""), search (e/watch !search)] !search isimli string bir atom tanımlandı ve bu atomun
;  (ui/input search (e/fn [v] (reset! !search v))