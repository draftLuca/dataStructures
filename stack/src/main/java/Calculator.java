
public class Calculator {

    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "700+2*6/2-40";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//用于保存每次扫描得到的字符
        String keepNum = "";//拼接多为数
        //开始扫描
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch 是字符 还是数字
            if (operStack.isOper(ch)) {
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //不为空，比较符号的优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //结果入栈
                        numStack.push(res);
                        //操作符入栈
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            } else {
//                numStack.push(ch - 48);
                //1.当是多位数时，不能发现以为数就立即入栈
                //2.处理时，需要再看后以为是否为符号，是才入栈
                keepNum += ch;
                //如果ch已经是expression最后一位，直接入栈
                if (index == expression.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个是否为数字，是数字继续扫描
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空
                        keepNum = "";
                    }
                }
            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //表达式扫描完毕
        while (true) {
            //如果符号栈为空，则计算到最后结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式结果：%s=%d", expression, numStack.pop());
    }
}

//数组实现 栈
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    //栈顶，初始化-1
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回当前栈顶的值，但不出栈
    public int peek() {
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈以满~");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序确定，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        //用于存放计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
