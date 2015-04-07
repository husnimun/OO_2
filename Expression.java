import java.util.*;

public abstract class Expression {
    
    // abstract class
    public abstract boolean isOperan(String opr);
    
    public abstract boolean isOperator(String oprt);
    
    public abstract boolean isUnaryOpt(String oprt);
    
    public abstract boolean higherPrecedence(String o1, String o2);
    
    public abstract int getOperatorWeight(String op);
    
    // data member
    protected String ekspresi;
    
    // constructor
    public Expression(String eks) {
        this.ekspresi = eks;
        if (isPostfix()) {
        } else if (isInfix()) {
            InfixToPostfix();
        } else {
            PrefixToPostfix();
        }
    }
    
    // setter and getter
    public void setEkspresi(String eks) {
        ekspresi = eks; 
    }
    
    public String getExpression() {
        return ekspresi;
    }
    
    private boolean isPrefix() {
        Scanner sc = new Scanner(ekspresi);
        String word_temp = sc.next();
        
        return isOperator(word_temp);
    }
    
    private boolean isInfix() {
        Scanner sc = new Scanner(ekspresi);
        String word_temp = sc.next();
        
        return !isOperator(word_temp) || word_temp.equals("(");
    }
    
    private boolean isPostfix() {
        Scanner sc = new Scanner(ekspresi);
        String word_temp = "";
        
        while (sc.hasNext()) {
            word_temp = sc.next();
        }
        
        return isOperator(word_temp) && !word_temp.equals("(") && !word_temp.equals(")");
    }
    
    private void PrefixToPostfix() {
        String word_temp = "", operan1, operan2, oprt;
        
        Stack<String> OpStack, RiversingStack;
        RiversingStack = new Stack<>();
        OpStack = new Stack<>();
        
        Scanner sc = new Scanner(ekspresi);
        
        while (sc.hasNext()) {
            word_temp = sc.next();
            RiversingStack.push(word_temp);
        }
        
        while (!RiversingStack.empty()) {
            if (isOperan(RiversingStack.peek())) {
                OpStack.push(RiversingStack.pop() + " ");
            } else {
                if (isUnaryOpt(RiversingStack.peek())) {
                    operan1 = OpStack.pop();
                    oprt = RiversingStack.pop();
                    OpStack.push(operan1 + oprt + " ");
                } else {
                    operan1 = OpStack.pop();
                    operan2 = OpStack.pop();
                    oprt = RiversingStack.pop();
                    OpStack.push(operan1 + operan2 + oprt + " ");
                }
                
            }
        }
        this.ekspresi = OpStack.pop();
    }
    
    private void InfixToPostfix() {
        Stack<String> OpStack = new Stack<>();
        String word_temp, PostFix = "";
        
        // memisahkan tanda "(" dan ")" agar tidak menempel dengan operan atau operator
        if (ekspresi.indexOf('(') != -1) {
            String[] split; 
            StringBuilder sb;

            split = ekspresi.split("\\("); 
            sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                sb.append(split[i]);
                 if (i != split.length - 1) {
                    sb.append("( ");
                }
            }
            ekspresi = sb.toString();

            split = ekspresi.split("\\)");
            sb = new StringBuilder();
            for (int i = 0; i < split.length; i++) {
                sb.append(split[i]);
                sb.append(" )");
            }
            ekspresi = sb.toString();
        }
        
        
        // mengubah postfix menjadi infix dengan algoritma Shunting Yard
        Scanner sc = new Scanner(ekspresi);
        while (sc.hasNext()) {
            word_temp = sc.next();
            
            if (isOperan(word_temp)) {
                PostFix += word_temp + " ";
                
            } else if (isOperator(word_temp)) {
                while (!OpStack.empty() && !OpStack.peek().equals("(") && higherPrecedence(OpStack.peek(), word_temp)) {
                    PostFix += OpStack.pop();
                    PostFix += " ";
                }
                OpStack.push(word_temp);
                
            } else if (word_temp.equals("(")) {
                OpStack.push(word_temp);
                
            } else if (word_temp.equals(")")) {
                while (!OpStack.empty() && !OpStack.peek().equals("(")) {
                    PostFix += OpStack.pop();
                    PostFix += " ";
                }
                OpStack.pop();
            }
        }
        
        while(!OpStack.empty()) {
            PostFix += OpStack.pop();
            PostFix += " ";
        }
        this.ekspresi = PostFix;
    }
}