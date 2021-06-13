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

    private final JPanel content;
    private final JTextField display;
    private JButton c, del, div, multi, minus, plus, value, point, equal,
            zero, one, two, three, four, five, six, seven, eight, nine;
    String firstNum, secNum, tempNum, operator;
    Color color;
    boolean second;

    public Calc() {
        super("Calculator");
        content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.LIGHT_GRAY);
        content.setPreferredSize(new Dimension(350,400));
        display = createDisplay();
        content.add(display);

        setAllB(content, c, "C", 10, 100, 80, 50);
        setAllB(content, del, "Del", 94, 100, 80, 50);
        setAllB(content, equal, "=", 177, 340, 163, 50);

        setAllOp(content, value, "+/-", 177, 100, 80, 50);
        setAllOp(content, div, "/", 260, 100, 80, 50);
        setAllOp(content, multi, "x", 260, 160, 80, 50);
        setAllOp(content, minus, "-", 260, 220, 80, 50);
        setAllOp(content, plus, "+", 260, 280, 80, 50);
        setAllOp(content, point, ".", 10, 340, 80, 50);

        setAllNum(content, seven, "7", 10,160,80,50);
        setAllNum(content, eight, "8", 94,160,80,50);
        setAllNum(content, nine, "9", 177,160,80,50);

        setAllNum(content, four, "4", 10,220,80,50);
        setAllNum(content, five, "5", 94,220,80,50);
        setAllNum(content, six, "6", 177,220,80,50);

        setAllNum(content, one, "1", 10,280,80,50);
        setAllNum(content, two, "2", 94,280,80,50);
        setAllNum(content, three, "3", 177,280,80,50);

        setAllNum(content, zero, "0", 94,340,80,50);

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

    void checkEndPoint(){
        if (firstNum.endsWith(".0")) {
            firstNum = firstNum.replace(".0", "");
        } else if(secNum.endsWith(".0")) {
            secNum = secNum.replace(".0", "");
        }
    }

    void result() {
        operator = "";
        secNum = "";
        second = false;
        checkEndPoint();
        tempNum = firstNum;
        display.setText(tempNum);
    }

    void setOp(String op){
        operator = op;
        second = true;
        secNum = "";
    }

    void printOp(){
        checkEndPoint();
        tempNum = firstNum + operator;
        display.setText(tempNum);
    }


    public void setAllB(JPanel p,JButton b, String s, int x, int y, int w, int h) {
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
                    case "=":
                        if (!second) return;
                        else {
                            display.setBackground(color);
                            switch (operator) {
                                case "/" -> {
                                    firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                    result();
                                }
                                case "x" -> {
                                    firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                    result();
                                }
                                case "+" -> {
                                    firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                    result();
                                }
                                case "-" -> {
                                    firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                    result();
                                }
                            }

                        }
                        break;
                }
            }
        }

        b = new JButton(s);
        b.setBounds(x,y,w,h);
        b.addActionListener(new button());

        p.add(b);
    }

    public void setAllOp(JPanel p,JButton b, String s, int x, int y, int w, int h) {
        class button implements ActionListener {
            public void actionPerformed(ActionEvent evt) {
                if (display.getText().length() < 9) {
                    switch (evt.getActionCommand()) {
                        case "+/-":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                else {
                                    firstNum = Double.toString(Double.parseDouble(firstNum)  * (-1));
                                    checkEndPoint();
                                    tempNum = firstNum;
                                    display.setText(tempNum);
                                }
                            } else {
                                if (secNum.equals("")) return;
                                else {
                                    String temp = secNum;
                                    secNum = Double.toString(Double.parseDouble(secNum)  * (-1));
                                    checkEndPoint();
                                    display.setText(tempNum.replace(temp, secNum));
                                }
                            }
                            break;
                        case "/":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                setOp("/");
                            } else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        setOp("/");
                                    }
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        setOp("/");
                                    }
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        setOp("/");
                                    }
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        setOp("/");
                                    }
                                }
                            }  printOp();
                            break;
                        case "x":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                setOp("x");
                            }  else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        setOp("x");
                                    }
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        setOp("x");
                                    }
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        setOp("x");
                                    }
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        setOp("x");
                                    }
                                }
                            }  printOp();
                            break;
                        case "+":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                setOp("+");
                            } else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        setOp("+");
                                    }
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        setOp("+");
                                    }
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        setOp("+");
                                    }
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        setOp("+");
                                    }
                                }
                            }  printOp();
                            break;
                        case "-":
                            if (!second) {
                                if (tempNum.equals("0")) return;
                                setOp("-");
                            } else {
                                if (tempNum.endsWith(operator)) display.setBackground(Color.PINK);
                                else {
                                    display.setBackground(color);
                                    if (operator.equals("-")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) - Double.parseDouble(secNum));
                                        setOp("-");
                                    }
                                    if (operator.equals("x")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) * Double.parseDouble(secNum));
                                        setOp("-");
                                    }
                                    if (operator.equals("/")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) / Double.parseDouble(secNum));
                                        setOp("-");
                                    }
                                    if (operator.equals("+")) {
                                        firstNum = Double.toString(Double.parseDouble(firstNum) + Double.parseDouble(secNum));
                                        setOp("-");
                                    }
                                }
                            }  printOp();
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
                    }
                }
                else display.setBackground(Color.PINK);
            }
        }

        b = new JButton(s);
        b.setBounds(x,y,w,h);
        b.addActionListener(new button());

        p.add(b);
    }

    public void setAllNum(JPanel p,JButton b, String s, int x, int y, int w, int h) {
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

        b = new JButton(s);
        b.setBounds(x,y,w,h);
        b.addActionListener(new button());

        p.add(b);
    }
}
