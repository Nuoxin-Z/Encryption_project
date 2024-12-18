class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  
  
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}


  void init() {
    // Encoding the plaintext:
    String file = Input.readFile("Original.txt");

    // Encode Level 1: Caesar Cipher
    String encodedMsg1 = caesarCipher(file, 3); // Shift by 3
    Input.writeFile("Encode1.txt", encodedMsg1);

    // Encode Level 2: Goldbug Cipher
    String encodedMsg2 = goldbugCipher(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);

    // Encode Level 3: Binary Cipher
    String encodedMsg3 = binaryCipher(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

    // Decoding the ciphertext:
    String file2 = Input.readFile("Encode3.txt");

    // Decode Level 1: Binary Cipher
    String decodedMsg1 = binaryDecipher(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);

    // Decode Level 2: Goldbug Cipher
    String decodedMsg2 = goldbugDecipher(decodedMsg1);
    Input.writeFile("Decode2.txt", decodedMsg2);

    // Decode Level 3: Caesar Cipher
    String decodedMsg3 = caesarCipher(decodedMsg2, -3); // Reverse shift by 3
    Input.writeFile("Decode3.txt", decodedMsg3);
  }
  
  
  
  // Caesar Cipher (Encoding and Decoding)
  String caesarCipher(String text, int shift) {
    StringBuilder result = new StringBuilder();
    shift = shift % 26; 

    for (char ch : text.toCharArray()) {
      if (Character.isLetter(ch)) {
        char base = Character.isUpperCase(ch) ? 'A' : 'a';
        result.append((char) ((ch - base + shift + 26) % 26 + base));
      } else {
        result.append(ch);
      }
    }
    return result.toString();
  }



  // Goldbug Cipher (Substitution Cipher)
  String goldbugCipher(String text) {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String cipher = "5w1d67f89op34qr2tnmsxzhy0abcuvkgl";
    StringBuilder result = new StringBuilder();

    for (char ch : text.toCharArray()) {
      if (Character.isLetter(ch)) {
        int index = alphabet.indexOf(Character.toLowerCase(ch));
        result.append(cipher.charAt(index));
      } else {
        result.append(ch);
      }
    }
    return result.toString();
  }
  // Goldbug Decipher
  String goldbugDecipher(String text) {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    String cipher = "5w1d67f89op34qr2tnmsxzhy0abcuvkgl";
    StringBuilder result = new StringBuilder();

    for (char ch : text.toCharArray()) {
      int index = cipher.indexOf(ch);
      if (index != -1) {
        result.append(alphabet.charAt(index));
      } else {
        result.append(ch);
      }
    }
    return result.toString();
  }



  // Binary Cipher (Convert to Binary)
  String binaryCipher(String text) {
    StringBuilder result = new StringBuilder();
    for (char ch : text.toCharArray()) {
      result.append(String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0')).append(" ");
    }
    return result.toString().trim();
  }

  // Binary Decipher (Convert from Binary)
  String binaryDecipher(String text) {
    String[] binaryChars = text.split(" ");
    StringBuilder result = new StringBuilder();

    for (String binary : binaryChars) {
      result.append((char) Integer.parseInt(binary, 2));
    }
    return result.toString();
  }
}
