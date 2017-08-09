package com.example.irisgyq.calculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Calculate {
    private InputProcess t = new InputProcess();
    private Queue<Node> q = new LinkedList<>();

    Tree<Node> createTree(List<Token> ar) {
        Tree<Node> t = new Tree<>();
        Node sub;
        int flag = 0;
        int pos1 = 0;
        int pos2 = 0;

        for (int i = 0; i < ar.size(); i = i + 2) {
            if (ar.get(i).getType().equals("Operator") && ar.get(i).getValue() == Operator.LEFT_BRACE.getValue()) {
                flag++;
                if (flag == 1) {
                    pos1 = i;
                }
                for (int j = i + 1; j < ar.size(); j++) {
                    if (ar.get(j).getType().equals("Operator") && ar.get(j).getValue() == Operator.LEFT_BRACE.getValue()) {
                        flag++;
                        if (flag == 1) {
                            pos1 = j;
                        }
                    }
                    if (ar.get(j).getType().equals("Operator") && ar.get(j).getValue() == Operator.RIGHT_BRACE.getValue()) {
                        flag--;
                        if (flag == 0) {
                            pos2 = j;
                            sub = createTree(ar.subList(pos1 + 1, pos2)).getRoot();
                            Node n1 = null;
                            Node n2 = null;
                            if(pos1 >= 1){
                                if(t.getRoot().getRight() != null){
                                    n1 = t.getRoot().getRight();
                                } else {
                                    n1 = t.getRoot();
                                }
                            }
                            if(pos2 <= ar.size() - 2){
                                n2 = new Node(ar.get(pos2 + 1).getValue(),ar.get(pos2 + 1).getType());
                            }
                            if (pos1 >= 1 && ar.get(pos1 - 1).getType().equals("Operator") && ar.get(pos1 - 1).getValue() != Operator.LEFT_BRACE.getValue()) {
                                if (pos2 <= ar.size() - 2 && ar.get(pos2 + 1).getType().equals("Operator") && ar.get(pos2 + 1).getValue() != Operator.RIGHT_BRACE.getValue()) {
                                    if (!compareOpe(ar.get(pos2 + 1), ar.get(pos1 - 1))) {
                                        n1.setRight(sub);
                                        if (compareOpe(ar.get(pos2 + 1), new Token(t.getRoot().getValue(),t.getRoot().getType()))) {
                                            n2.setLeft(n1);
                                            t.getRoot().setRight(n2);
                                        } else {
                                            n2.setLeft(t.getRoot());
                                            t.setRoot(n2);
                                        }
                                        t.setRoot(n2);
                                    } else {
                                        n2.setLeft(sub);
                                        n1.setRight(n2);
                                    }
                                } else {
                                    n1.setRight(sub);
                                }
                            } else if (pos2 <= ar.size() - 2 && ar.get(pos2 + 1).getType().equals("Operator") && ar.get(pos2 + 1).getValue() != Operator.RIGHT_BRACE.getValue()) {
                                n2.setLeft(sub);
                                t.setRoot(n2);
                            } else {
                                t.setRoot(sub);
                            }
                        }
                    }
                }
                i = pos2;
            } else {
                Node n4 = new Node(ar.get(i).getValue(),ar.get(i).getType());
                if (i == ar.size() - 1 && n4.getType().equals("Number")) {
                    if (i == 0) {
                        t.setRoot(n4);
                        break;
                    }
                    Node n = t.getRoot();
                    while (n.getRight() != null) {
                        n = n.getRight();
                    }
                    n.setRight(n4);
                } else {
                    Node n5 = new Node(ar.get(i + 1).getValue(),ar.get(i + 1).getType());
                    Node n3 = null;
                    if(i>=2){
                        if(t.getRoot().getRight() != null){
                            n3 = t.getRoot().getRight();
                        } else {
                            n3 = t.getRoot();
                        }
                    }
                    if (n5.getType().equals("Operator") && n4.getType().equals("Number")) {
                        n5.setLeft(n4);
                    }
                    if (n5.getType().equals("Operator") && i == 0) {
                        t.setRoot(n5);
                    } else if (i >= 2 && compareOpe(ar.get(i + 1), ar.get(i - 1))) {
                        n3.setRight(n5);
                    } else {
                        n3.setRight(n4);
                        if (compareOpe(ar.get(i + 1), new Token(t.getRoot().getValue(), t.getRoot().getType()))) {
                            n5.setLeft(n3);
                            t.getRoot().setRight(n5);
                        } else {
                            n5.setLeft(t.getRoot());
                            t.setRoot(n5);
                        }
                    }
                }
            }
        }
        return t;
    }

    private boolean compareOpe(Token op1, Token op2) {
        switch (op1.getValue()) {
            case 42: {
                return (op2.getValue() == 43 || op2.getValue() == 45);
            }
            case 47: {
                return (op2.getValue() == 43 || op2.getValue() == 45);
            }
            case 43: {
                return false;
            }
            case 45: {
                return false;
            }
            default:
                return false;
        }
    }

    double calculate(Tree<Node> tr) {
        postOrder(tr.getRoot());
        double num1, num2;
        Stack<Double> val = new Stack<>();
        while (!q.isEmpty()) {
            Node n = q.peek();
            if (n.getType().equals("Number")) {
                val.push((double) q.poll().getValue());
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.ADD.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num1 + num2);
                q.poll();
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.SUB.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num2 - num1);
                q.poll();
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.MUL.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num1 * num2);
                q.poll();
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.DIV.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                if (num1 == 0) {
                    Constants.resS="Divisor can't be zero.";

                }
                val.push(num2 / num1);
                q.poll();
            }
        }
        return val.pop();
    }

    private void postOrder(Node root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            q.add(root);
        }
    }
}