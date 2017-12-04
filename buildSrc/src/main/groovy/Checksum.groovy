import java.security.MessageDigest
import java.io.*

/**
 * Determine the MD5 or SHA1Sum of a file:
 *
 *   println Checksum.getMD5Sum(new File("file.bin"))
 *   println Checksum.getSHA1Sum(new File("file.bin"))
 *
 */
public class Checksum {

    static getSha1sum(file) {
        return getChecksum(file, "SHA1")
    }

    static getMD5sum(file) {
        return getChecksum(file, "MD5")
    }

    static getChecksum(file, type) {
        def digest = MessageDigest.getInstance(type)
        def inputstream = file.newInputStream()
        def buffer = new byte[16384]
        def len

        while((len=inputstream.read(buffer)) > 0) {
            digest.update(buffer, 0, len)
        }
        def sha1sum = digest.digest()

        def result = ""
        for(byte b : sha1sum) {
            result += toHex(b)
        }
        return result
    }

    static String md5(String s) {
        MessageDigest digest = MessageDigest.getInstance("MD5")
        digest.update(s.bytes);
        new BigInteger(1, digest.digest()).toString(16).padLeft(32, '0')
    }
    private static hexChr(int b) {
        return Integer.toHexString(b & 0xF)
    }

    private static toHex(int b) {
        return hexChr((b & 0xF0) >> 4) + hexChr(b & 0x0F)
    }
}