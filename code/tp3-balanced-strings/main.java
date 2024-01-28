public static boolean isBalanced(String str) {
        Objects.requireNonNull(str);
        if(str.isEmpty()) {
            return true;
        }
        Stack<String> tas = new Stack<>();

        for(String c : str.split("")) {
            if (c.equals("(") || c.equals("{") || c.equals("[")) {
                tas.push(c);
            } else if (c.equals(")") || c.equals("}") || c.equals("]")) {
                if (tas.isEmpty()) {
                    return false;
                }
                String last = tas.pop();
                if (c.equals(")") && !last.equals("(")) {
                    return false;
                }
                if (c.equals("}") && !last.equals("{")) {
                    return false;
                }
                if (c.equals("]") && !last.equals("[")) {
                    return false;
                }
            }
        }
        return tas.isEmpty();
    }

