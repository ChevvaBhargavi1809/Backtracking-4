// Time Complexity : O(n+nCk) where k = avg length of pattern in {}
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : np(premium)
// Any problem you faced while coding this : no
// Approach : first split the pattern into list of charcters if in {} else just single character. Now use backtracking to add single charcter to stringbuilder obj if in {}, backtrack and recurse

class BraceExpansion{
    public List<String> expand(String pattern){
        List<String> result = new ArrayList<>();
        List<List<Character>> possPatterns = new ArrayList<>();
        int i=0;
        while(i<pattern.length()){
            char ch = pattern.charAt(i);
            List<Character> singlePattern = new ArrayList<>();
            if(ch=='{'){
                i++;
                while(pattern.charAt(i)!='}')
                {
                    if(pattern.charAt(i) == ','){
                        i++;
                        continue;
                    }
                    else{
                        singlePattern.add(pattern.charAt(i));
                    }
                    i++;
                }
                
            }
            else{
                singlePattern.add(ch);
            }
            possPatterns.add(singlePattern);
            i++;
        }
        //System.out.println(possPatterns);
        helper(possPatterns, 0, result, new StringBuilder());
        return result;
    }
    private void helper(List<List<Character>> patterns, int idx, List<String> result, StringBuilder path){
        if(idx == patterns.size()){
            result.add(path.toString());
            return;
        }
        List<Character> li = patterns.get(idx);
        for(int i=0;i<li.size();i++){
            path.append(li.get(i));
            helper(patterns, idx+1, result, path);
            path.setLength(path.length()-1);
        }
    }
}
class Main{
    public static void main(String args[]){
        String pattern = "{a,b}cd{e,f}";
        BraceExpansion obj = new BraceExpansion();
        System.out.println(obj.expand(pattern));
    }
}