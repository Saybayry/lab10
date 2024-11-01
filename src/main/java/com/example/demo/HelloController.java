    package com.example.demo;

    import javafx.event.ActionEvent;
    import javafx.event.EventHandler;
    import javafx.fxml.FXML;
    import javafx.geometry.Insets;
    import javafx.scene.control.*;
    import javafx.scene.input.MouseEvent;

    public class HelloController {
        @FXML
        private Label welcomeText;

        @FXML
        private Button calculate_btn_one;
        @FXML
        private TextArea answer_one;
        @FXML
        private TextArea x_one;
        @FXML
        private TextArea y_one;
        @FXML
        private TextArea z_one;

        @FXML
        private Button calculate_btn_two;
        @FXML
        private TextArea x_two;
        @FXML
        private TextArea y_two;
        @FXML
        private TextArea answer_two;

        @FXML
        private RadioButton radioButton1;
        @FXML
        private RadioButton radioButton2;
        @FXML
        private RadioButton radioButton3;


        ToggleGroup toggleGroup = new ToggleGroup();

        @FXML
        public void initialize(){
            calculate_btn_one.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    float x = Float.parseFloat(x_one.getText());
                    float y = Float.parseFloat(y_one.getText());
                    float z = Float.parseFloat(z_one.getText());
                    double answer = CalculateLinnear(x,y,z);
                    answer_one.setText(String.valueOf(answer));

                }
            });
            calculate_btn_two.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    float x = Float.parseFloat(x_two.getText());
                    float y = Float.parseFloat(y_two.getText());
                    double answer = 0;
                    try {
                        answer = CalculateBranching(x,y);
                        answer_two.setText(String.valueOf(answer));
                    } catch (Exception e) {
                        answer_two.setText("Некоректые данные");
                    }


                }
            });
            radioButton1.setToggleGroup(toggleGroup);
            radioButton2.setToggleGroup(toggleGroup);
            radioButton3.setToggleGroup(toggleGroup);
        }


        private double CalculateLinnear(float x, float y,float z){
            double  anser;
            anser = (Math.cbrt(8 + Math.pow(x- y,2)+1 ) / x*x+y*y+2) - Math.pow(Math.exp(Math.abs(x-y)*(Math.pow(Math.tan(z),2)+1 ) ) ,x) ;

            return anser ;
        }
        @FXML
        private void Linear_answer_btn(ActionEvent event) {
            System.out.println("Button clicked!");
            answer_one.setText("error!");
        }

        private  double CalculateBranching(float x, float y) throws Exception {
            if( (3<x*y) & (x*y<8) ){
                return Math.log(Math.abs(y+f_for_x(x)));
            } else if (x*y>12) {
                return Math.cos(f_for_x(x))-y;
            }else {
                return Math.sinh(f_for_x(x) +Math.cosh(y));
            }
        }
        private double f_for_x(double x) throws Exception {
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();

            if (selectedRadioButton != null) {
                String f_name = selectedRadioButton.getText(); // Получаем текст выбранной радио-кнопки

                if (f_name == radioButton1.getText()){
                    return Math.cos(x);
                } else if (f_name == radioButton2.getText()) {
                    return Math.sqrt(x);
                } else if (f_name == radioButton3.getText()) {
                    return Math.exp(x);
                }
            }


            throw new Exception("Произошла ошибка"); // Бросает общее исключение с сообщением
        }





}