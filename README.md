# Encryption algorithms

in this project, I have implemented some encryption algorithms. and make Command Line interface (CLI) for them.

## Installation

```bash
git clone https://github.com/SalahTawafsha/encreption-algorithms.git
```

## App Usage

<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/476fe4f7-5cdc-49cc-bc43-3aa48306d3ac" alt="CLI of the algorithms">
<br>
so that user can choose the algorithm he wants to use and then enter the text he wants to encrypt or decrypt.

## 1. Advanced Encryption Standard (AES)

[Advanced Encryption Standard (AES)](https://www.geeksforgeeks.org/advanced-encryption-standard-aes/) is a symmetric
encryption algorithm. it is a block cipher that encrypts 128-bit blocks of data. The key size can be 128, 192, or 256
bits.
<br><br>
When select AES. the user will be asked to enter if he wants to encrypt or decrypt the text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/51948d20-7698-456e-8ecd-cef2e01edc3c" alt="AES options">

### 1.1. Encryption

in encryption, the user will be asked to enter the 32-byte key as HEX and the text he wants to encrypt, then he will get
the
ciphertext.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/1e812e24-5ba3-4aec-bdd5-44f0cd0c9a56" alt="AES Encryption">

### 1.2. Decryption

in decryption, the user will be asked to enter the 32-byte key as HEX and the ciphertext he wants to decrypt, then he
will get
the original text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/6904cfa3-3943-4720-be83-7abd06db28e1" alt="AES Decryption">

## 2. Rivest-Shamir-Adleman (RSA)

[Rivest-Shamir-Adleman (RSA)](https://www.geeksforgeeks.org/rsa-algorithm-cryptography/) is an asymmetric encryption
algorithm. it is a block cipher that encrypts 128-bit blocks of data. The key size can be 128, 192, or 256 bits.
<br><br>
When select RSA. the user will be asked to enter if he wants to encrypt or decrypt the text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/de1a917a-9871-4808-b0b5-d09ab2534d80" alt="RSA options">

### 2.1. Encryption

in encryption, the user will be asked to enter the plaintext and system will generate the public and private keys, then
he will get the keys with ciphertext as hex.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/9733fa02-8996-4b92-b47c-83e9a95167da" alt="RSA Encryption">

### 2.2. Decryption

in decryption, the user will be asked to enter the keys and the ciphertext he wants to decrypt, then he will get the
original text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/2b79459b-4b3c-4e28-a1ba-ee42fdb39522" alt="RSA Decryption">

## 3. Columnar Cipher

[Columnar Cipher](https://www.geeksforgeeks.org/columnar-transposition-cipher/) is a transposition cipher. and I updated
it to can use any characters in the key.
<br><br>
When select Columnar Cipher. the user will be asked to enter if he wants to encrypt or decrypt the text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/041135f5-a550-4743-86ee-ef78200abc48" alt="Columnar Cipher options">

### 3.1. Encryption

in encryption, the user will be asked to enter the key and the text he wants to encrypt, then he will get the
ciphertext.
<br>
Note: the key is string of any characters but must be unique characters.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/fa708c1d-a396-4fe1-a7cb-422a7ac3f335" alt="Columnar Cipher Encryption">

### 3.2. Decryption

in decryption, the user will be asked to enter the key and the ciphertext he wants to decrypt, then he will get the
original text.
<br>
Note: the key is string of any characters but must be unique characters.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/7206553c-fffd-4fea-8621-ca64720780b8" alt="Columnar Cipher Decryption">

## 4. PlayFair Cipher

[PlayFair Cipher](https://www.geeksforgeeks.org/playfair-cipher-with-examples/) is a substitution cipher. and I updated
it to be 16 X 16 matrix and can be used for any kind of characters.
<br><br>
When select PlayFair Cipher. the user will be asked to enter if he wants to encrypt or decrypt the text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/3ec18a82-235e-4244-ba36-a6f91754613d" alt="PlayFair Cipher options">

### 4.1. Encryption

In encryption, the user will be asked to enter the text he wants to encrypt, then he will get the ciphertext.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/773d3839-fdf9-48f8-a934-f6553bfafed2" alt="PlayFair Cipher Encryption">

### 4.2. Decryption

In decryption, the user will be asked to enter the ciphertext he wants to decrypt, then he will get the original text.
<br>
<img src="https://github.com/SalahTawafsha/encreption-algorithms/assets/93351227/596c10e6-6b96-4651-9368-6d165cb1c761" alt="PlayFair Cipher Decryption">