import org.bouncycastle.crypto.generators.SCrypt;

object MyApp {

  val SALT: String = "abc123";

  // DifficultyFactor
  // These should be powers of 2
  val cpu: Int = 8;
  val memory: Int = 8;
  val parallelism: Int = 8;
  val outputLength: Int = 32;

  def hashPsw(plainText: String): String = {
      val hash = SCrypt.generate(plainText.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);
      return new String(hash);
  }

  def checkPsw(plainText: String, hashedStr: String):  Boolean = {
      val hash = SCrypt.generate(plainText.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);
      val stored = new String(hash);
      return stored.equals(hashedStr);
  }

  def main(args: Array[String]): Unit = {
    val plainText = "Hello World!";
    val stored = hashPsw(plainText);
    val isMatch = checkPsw(plainText, stored);

    println("Original: " + plainText);
    println("Hash: " + stored);
    println("Verified: " + isMatch);
  }
}
