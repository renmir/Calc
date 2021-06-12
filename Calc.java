import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame {

    public static void main(String[] args) {
        JFrame window = new Calc();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private final JPanel content  = new JPanel();
    private final JTextField display;
    private JButton c, del, div, multi, minus, plus, value, point, equal;
    private JButton zero, one, two, three, four, five, six, seven, eight, nine;
    String firstNum, secNum, tempNum, operator;
    Color color;
    boolean second;

    public Calc() {
        super("Calculator");
        content.setLayout(null);
        content.setBackground(Color.LIGHT_GRAY);
        content.setPreferredSize(new Dimension(350,400));
        display = createDisplay();
        content.add(display);

        setCDel();
        content.add(c);
        content.add(del);

        setAllOp();
        content.add(value);
        content.add(div);
        content.add(multi);
        content.add(minus);
        content.add(plus);
        content.add(point);
        content.add(equal);

        setAllNum();
        content.add(seven);
        content.add(eight);
        content.add(nine);
        content.add(four);
        content.add(five);
        content.add(six);
        content.add(one);
        content.add(two);
        content.add(three);
        content.add(zero);

        setContentPane(content);
        pack();
        setLocationRelativeTo(null);
    }

    public JTextField createDisplay() {
        JTextField display = new JTextField();
        display.setText(tempNum = "0");
        operator = "";
        second = false;
        color = display.getBackground();
        display.setBounds(10,10,330,80);
        display.setFont(new Font("Arial", Font.PLAIN, 62));
        display.setEditable(false);
        return display;
    }

    public void setCDel(){
        class button implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                switch (evt.getActionCommand()) {
                    case "C":
                        firstNum = "0";
                        operator = "";
                        second = false;
                        display.setBackground(color);
                        display.setText(tempNum = "0");
                        break;
                    case "Del":
                        if (tempNum.equals("0")) return;
                        else {
                            if (tempNum.endsWith(operator)) {
                                operator = "";
                                second = false;
                                display.setText(tempNum = tempNum.substring(0, tempNum.length() - 1));
                                firstNum = tempNum;
                            } else {
                                display.setText(tempNum = tempNum.substring(0, tempNum.length() - 1));
                                if (!second) firstNum = tempNum;
                                else {
                                    secNum = tempNum.replace(firstNum + operator, "");
                                }
                            }
                            if (tempNum.equals("-") || tempNum.length() == 0) {
                                firstNum = "0";
                                display.setText(tempNum = "0");
                            }
                            if (display.getText().length() < 9) {
                                display.setBackground(color);
                            }
                        }
                        break;
                }
            }
        }

        c = new JButton("C");
        c.setBounds(10,100,80,50);
        c.addActionListener(new button());
        del = new JButton("Del");
        del.setBounds(94,100,80,50);
        del.addActionListener(new button());
    }

    public void setAllOp() {
        class button implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                if (display.getText().length() < 9) {
                    switch (evt.getActionCommand()) {
                        case "+/-":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                else {
                                    firstNum = Double.toString(Double.parseDouble(firstNum)  * (-1));
                                    tempNum = firstNum;
                                    display.setText(tempNum);
                                    if (tempNum.endsWith(".0")) {
                                        firstNum = firstNum.replace(".0", "");
                                        display.setText(tempNum = firstNum);
                                    }
                                }
                            } else {
                                if (secNum.equals("")) return;
                                else {
                                    String temp = secNum;
                                    secNum = Double.toString(Double.parseDouble(secNum)  * (-1));
                                    if (secNum.endsWith(".0")) secNum = secNum.replace(".0", "");
                                    display.setText(tempNum.replace(temp, secNum));
                                }
                            }
                            break;
                        case "/":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                operator = "/";
                                tempNum = firstNum + operator;
                                display.setText(tempNum);
                                second = true;
                                secNum = "";
                            } else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        operator = "/";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        operator = "/";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        operator = "/";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                }
                                if (firstNum.endsWith(".0")) {
                                    firstNum = firstNum.replace(".0", "");
                                    display.setText(tempNum = firstNum + operator);
                                }
                            }
                            break;
                        case "x":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                operator = "x";
                                tempNum = firstNum + operator;
                                display.setText(tempNum);
                                second = true;
                                secNum = "";
                            }  else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        operator = "x";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        operator = "x";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        operator = "x";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                }
                                if (firstNum.endsWith(".0")) {
                                    firstNum = firstNum.replace(".0", "");
                                    display.setText(tempNum = firstNum + operator);
                                }
                            }
                            break;
                        case "+":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                operator = "+";
                                tempNum = firstNum + operator;
                                display.setText(tempNum);
                                second = true;
                                secNum = "";
                            } else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        operator = "+";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        operator = "+";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                }
                                if (operator.equals("-")) {
                                    firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                    operator = "+";
                                    tempNum = firstNum + operator;
                                    secNum = "";
                                    display.setText(tempNum);
                                }
                            }
                            break;
                        case "-":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                operator = "-";
                                tempNum = firstNum + operator;
                                display.setText(tempNum);
                                second = true;
                                secNum = "";
                            } else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        operator = "-";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        operator = "-";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        operator = "-";
                                        tempNum = firstNum + operator;
                                        secNum = "";
                                        display.setText(tempNum);
                                    }
                                }
                                if (operator.equals("-")) {
                                    firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                    operator = "+";
                                    tempNum = firstNum + operator;
                                    secNum = "";
                                    display.setText(tempNum);
                                }
                                if (firstNum.endsWith(".0")) {
                                    firstNum = firstNum.replace(".0", "");
                                    display.setText(tempNum = firstNum + operator);
                                }
                            }
                            break;
                        case ".":
                            if (!second) {
                                if (tempNum.equals("0")) {
                                    tempNum += ".";
                                    firstNum = tempNum;
                                    display.setText(tempNum);
                                }
                                else {
                                    if (firstNum.contains(".")) return;
                                    else {
                                        String temp = firstNum;
                                        firstNum += ".";
                                        tempNum = tempNum.replace(temp, firstNum);
                                        display.setText(tempNum);
                                    }
                                }
                            } else {
                                if (secNum.contains(".")) return;
                                else {
                                    String temp = secNum;
                                    secNum += ".";
                                    tempNum = tempNum.replace(temp, secNum);
                                    display.setText(tempNum);
                                }
                            }
                            break;
                        case "=":
                            if (!second) return;
                            else {
                                display.setBackground(color);
                                switch (operator) {
                                    case "/" -> {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        tempNum = firstNum;
                                        secNum = "";
                                        second = false;
                                        display.setText(tempNum);
                                    }
                                    case "x" -> {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        tempNum = firstNum;
                                        secNum = "";
                                        second = false;
                                        display.setText(tempNum);
                                    }
                                    case "+" -> {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        tempNum = firstNum;
                                        secNum = "";
                                        second = false;
                                        display.setText(tempNum);
                                    }
                                    case "-" -> {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        tempNum = firstNum;
                                        secNum = "";
                                        second = false;
                                        display.setText(tempNum);
                                    }
                                }
                                if (tempNum.endsWith(".0")) {
                                    firstNum = firstNum.replace(".0", "");
                                    display.setText(tempNum = firstNum);
                                }
                            }
                            break;
                    }
                }
                else display.setBackground(Color.PINK);
            }
        }

        value = new JButton("+/-");
        value.setBounds(177,100,80,50);
        value.addActionListener(new button());

        div = new JButton("/");
        div.setBounds(260,100,80,50);
        div.addActionListener(new button());
        multi = new JButton("x");
        multi.setBounds(260,160,80,50);
        multi.addActionListener(new button());
        minus = new JButton("-");
        minus.setBounds(260,220,80,50);
        minus.addActionListener(new button());
        plus = new JButton("+");
        plus.setBounds(260,280,80,50);
        plus.addActionListener(new button());

        point = new JButton(".");
        point.setBounds(10,340,80,50);
        point.addActionListener(new button());
        equal = new JButton("=");
        equal.setBounds(177,340,163,50);
        equal.addActionListener(new button());
    }

    public void setAllNum() {
        class button implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                if (display.getText().length() < 9) {  // max length for input
                    display.setBackground(color);
                    switch (evt.getActionCommand()) {
                        case "0":
                            if (tempNum.equals("0")) return;
                            else {
                                display.setText(tempNum += 0);
                                if (!second) firstNum += 0;
                                if (second) secNum += 0;
                            }
                            break;
                        case "1":
                            if (tempNum.equals("0")) {
                                firstNum = "1";
                                display.setText(tempNum = "1");
                            }
                            else {
                                display.setText(tempNum += 1);
                                if (!second) firstNum += 1;
                                if (second) secNum += 1;
                            }
                            break;
                        case "2":
                            if (tempNum.equals("0")) {
                                firstNum = "2";
                                display.setText(tempNum = "2");
                            }
                            else {
                                display.setText(tempNum += 2);
                                if (!second) firstNum += 2;
                                if (second) secNum += 2;
                            }
                            break;
                        case "3":
                            if (tempNum.equals("0")) {
                                firstNum = "3";
                                display.setText(tempNum = "3");
                            }
                            else {
                                display.setText(tempNum += 3);
                                if (!second) firstNum += 3;
                                if (second) secNum += 3;
                            }
                            break;
                        case "4":
                            if (tempNum.equals("0")) {
                                firstNum = "4";
                                display.setText(tempNum = "4");
                            }
                            else {
                                display.setText(tempNum += 4);
                                if (!second) firstNum += 4;
                                if (second) secNum += 4;
                            }
                            break;
                        case "5":
                            if (tempNum.equals("0")) {
                                firstNum = "5";
                                display.setText(tempNum = "5");
                            }
                            else {
                                display.setText(tempNum += 5);
                                if (!second) firstNum += 5;
                                if (second) secNum += 5;
                            }
                            break;
                        case "6":
                            if (tempNum.equals("0")) {
                                firstNum = "6";
                                display.setText(tempNum = "6");
                            }
                            else {
                                display.setText(tempNum += 6);
                                if (!second) firstNum += 6;
                                if (second) secNum += 6;
                            }
                            break;
                        case "7":
                            if (tempNum.equals("0")) {
                                firstNum = "7";
                                display.setText(tempNum = "7");
                            }
                            else {
                                display.setText(tempNum += 7);
                                if (!second) firstNum += 7;
                                if (second) secNum += 7;
                            }
                            break;
                        case "8":
                            if (tempNum.equals("0")) {
                                firstNum = "8";
                                display.setText(tempNum = "8");
                            }
                            else {
                                display.setText(tempNum += 8);
                                if (!second) firstNum += 8;
                                if (second) secNum += 8;
                            }
                            break;
                        case "9":
                            if (tempNum.equals("0")) {
                                firstNum = "9";
                                display.setText(tempNum = "9");
                            }
                            else {
                                display.setText(tempNum += 9);
                                if (!second) firstNum += 9;
                                if (second) secNum += 9;
                            }
                            break;
                    }
                }
                else display.setBackground(Color.PINK);
            }
        }

        seven = new JButton("7");
        seven.setBounds(10,160,80,50);
        seven.addActionListener(new button());
        eight = new JButton("8");
        eight.setBounds(94,160,80,50);
        eight.addActionListener(new button());
        nine = new JButton("9");
        nine.setBounds(177,160,80,50);
        nine.addActionListener(new button());

        four = new JButton("4");
        four.setBounds(10,220,80,50);
        four.addActionListener(new button());
        five = new JButton("5");
        five.setBounds(94,220,80,50);
        five.addActionListener(new button());
        six = new JButton("6");
        six.setBounds(177,220,80,50);
        six.addActionListener(new button());

        one = new JButton("1");
        one.setBounds(10,280,80,50);
        one.addActionListener(new button());
        two = new JButton("2");
        two.setBounds(94,280,80,50);
        two.addActionListener(new button());
        three = new JButton("3");
        three.setBounds(177,280,80,50);
        three.addActionListener(new button());

        zero = new JButton("0");
        zero.setBounds(94,340,80,50);
        zero.addActionListener(new button());
    }
}