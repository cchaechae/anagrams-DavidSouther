/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    private ArrayList<String> list = new ArrayList<String>();

    //pass a whole list of words and store that into memory
    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();

            //
            //  Your code here
            //new array list for the whole list of strings
            //from the hardware to the memory
            list.add(word);
            //lettersToWord.put(word, list);
            String temp = sortLetters(word);

            //Hash Map of set of sorted word characters
            if (!lettersToWord.containsKey(temp)){
                lettersToWord.put(temp,new ArrayList<String>());
            }

            lettersToWord.get(temp).add(word);
        }
    }

    //word and base are anagrams but not same
    public boolean isGoodWord(String word, String base) {
        //
        // Your code here
//        if (isAnagram(word.toLowerCase(), base.toLowerCase()) && !word.equals(base))
//            return true;
//
//        else
//            return false;

        return getAnagramsWithOneMoreLetter(base).contains(word);
    }

    //get a list of all possible anagrams given the target word
    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        // Your code here
        //go through that master list, and if the anagrams
//        for (String word: list){
//            if (isAnagram(word, targetWord)){
//
//                result.add((word));
//
//            }
//        }

        String key = sortLetters(targetWord);

//        if(lettersToWord.containsKey(key)){
//
//            result.add(targetWord);
//        }

        if (!lettersToWord.containsKey(key)){
            return result;
        }

        return lettersToWord.get(key);
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        //
        // Your code here
        if (!sortLetters(first).equals(sortLetters(second)))
            return false;
        else
            return true;
    }

    @VisibleForTesting
    static String sortLetters(String input) {
        char[] chars = input.toCharArray();
        //
        // Your code here
        Arrays.sort(chars);

        String result = new String(chars);

        return result;
    }

    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        // Your code here
//        for (String w: getAnagrams(word)){
//
//            char[] charArray = w.toCharArray();
//            if (charArray.)
//        }

        for (char c: alphabet){
            String temp = sortLetters(word + c);
            result.addAll(getAnagrams(temp));
        }
        return result;
    }

    public String pickGoodStarterWord() {

        String goodword = "";
        ArrayList<String> goodList = new ArrayList<String>();
        List<String> anagramList = new ArrayList<String>();

        for (String w: list){

            if (w.length() >= DEFAULT_WORD_LENGTH && w.length()<MAX_WORD_LENGTH){

                //lettersToWord.get(w).size()>=MIN_NUM_ANAGRAMS &&
                anagramList = getAnagramsWithOneMoreLetter(w);
                if (anagramList.size()>= MIN_NUM_ANAGRAMS)
                    goodList.add(w);
            }
        }

//        goodList = getAnagramsWithOneMoreLetter(w);
//        if (goodList.size()>= MIN_NUM_ANAGRAMS)
//            goodList.add(w);

        int randomNum = (int) Math.floor(Math.random() * goodList.size());
        goodword = goodList.get(randomNum);

        return goodword;
    }
}
