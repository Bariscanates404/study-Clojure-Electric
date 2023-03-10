(ns app.P03-BmiCalculator
  (:require [hyperfiddle.electric :as e]
            [hyperfiddle.electric-dom2 :as dom]
            [hyperfiddle.electric-ui4 :as ui]
            ))



(defn women-bmi [w h s] ((s * w) / (h * h)))
(defn men-bmi [w h s] ((s * w) / (h * h)))

(e/defn App []
        (let [!state (atom {:in "" :in2 "" :in3 "" :w "" :h "" :s "" :button1 "" :button2 "" :button3 "" :bg-color "black" :bg-color2 "black" :bg-color3 "black" :placeholder "..."})]
          (let [in (get (e/watch !state) :in) in2 (get (e/watch !state) :in2) in3 (get (e/watch !state) :in3) ]
            (e/client
              (dom/h1 (dom/text "BMI Index Calculator"))

              (dom/dl
                (dom/dt (dom/text "Gender"))
                (dom/dd
                  (ui/input in (e/fn [v] (swap! !state assoc :in v))
                            (dom/props {:id 1 :placeholder (get (e/watch !state) :placeholder) :style {:background-color (get (e/watch !state) :bg-color)}})
                            (dom/on "keydown" (e/fn [enter]
                                                    (when (= "Enter" (.-key enter))
                                                      (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                        (swap! !state assoc :s givenValue)
                                                        (set! (.-value dom/node)
                                                              )))))
                            (dom/on "keyup" (e/fn [keyup]
                                                  (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                    (swap! !state assoc :button1 givenValue)
                                                    )))
                            ))
                (dom/dt (dom/text "Weight (cm)"))
                (dom/dd
                  (ui/input in2 (e/fn [v] (swap! !state assoc :in2 v))
                            (dom/props {:placeholder (get (e/watch !state) :placeholder) :style {:background-color (get (e/watch !state) :bg-color2)}})
                            (dom/on "keydown" (e/fn [enter]
                                                    (when (= "Enter" (.-key enter))
                                                      (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                        (swap! !state assoc :w givenValue)
                                                        (set! (.-value dom/node)
                                                              )))))
                            (dom/on "keyup" (e/fn [keyup]
                                                  (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                    (swap! !state assoc :button2 givenValue)
                                                    (reset! weight (read-string givenValue))
                                                    )))
                            ))
                (dom/dt (dom/text "Height (cm)"))
                (dom/dd
                  (ui/input in3 (e/fn [v] (swap! !state assoc :in3 v))
                            (dom/props {:placeholder (get (e/watch !state) :placeholder) :style {:background-color (get (e/watch !state) :bg-color3)}})
                            (dom/on "keydown" (e/fn [enter]
                                                    (when (= "Enter" (.-key enter))
                                                      (when-some [givenValue (contrib.str/empty->nil (-> enter .-target .-value))]
                                                        (swap! !state assoc :h givenValue)
                                                        (set! (.-value dom/node)
                                                              )))))
                            (dom/on "keyup" (e/fn [keyup]
                                                  (when-some [givenValue (contrib.str/empty->nil (-> keyup .-target .-value))]
                                                    (swap! !state assoc :button3 givenValue)
                                                    (reset! height (read-string givenValue))
                                                    )))
                            ))
                )
              (ui/button
                (e/fn [] ((swap! !state assoc :s (get (e/watch !state) :button1))
                          (swap! !state assoc :w (get (e/watch !state) :button2))
                          (swap! !state assoc :h (get (e/watch !state) :button3))
                          (swap! !state assoc :bg-color3 "inherit")
                          (swap! !state assoc :bg-color2 "inherit")
                          (swap! !state assoc :bg-color "inherit")
                          (swap! !state assoc :in "")
                          (swap! !state assoc :in2 "")
                          (swap! !state assoc :in3 "")
                          ))
                (dom/text "Calculate!!!")
                (dom/props {:class "Button" :style {:color "white" :background-color "black"}}))
              (dom/h3 (dom/text "Result is: " (read-string (get (e/watch !state) :button1))))
              ))))



;(pr-str (men-bmi (get (e/watch !state) :button2) (get (e/watch !state) :button3) (get (e/watch !state) :button1)))