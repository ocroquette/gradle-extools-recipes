import java.nio.file.Files

class CompressUtils {
    static def extractXz(def inputPath, def outputPath) {
        InputStream fin = Files.newInputStream(inputPath)
        BufferedInputStream bin = new BufferedInputStream(fin)
        OutputStream out = Files.newOutputStream(outputPath)
        int buffersize = 16 * 1024 * 1024
        org.apache.commons.compress.compressors.xz.XZCompressorInputStream xzIn = new org.apache.commons.compress.compressors.xz.XZCompressorInputStream(bin)
        final byte[] buffer = new byte[buffersize]
        int n = 0
        while (-1 != (n = xzIn.read(buffer))) {
            out.write(buffer, 0, n)
        }
        out.close()
        xzIn.close()
    }

    static def extract7z(def inputFile, def outputDir) {
        org.apache.commons.compress.archivers.sevenz.SevenZFile sevenZFile = new org.apache.commons.compress.archivers.sevenz.SevenZFile(inputFile);
        org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry entry = sevenZFile.getNextEntry();
        while (entry != null) {
            if (entry.isDirectory()) {
                new File(outputDir, entry.getName()).mkdirs()
            } else {
                FileOutputStream out = new FileOutputStream(new File(outputDir, entry.getName()));
                byte[] content = new byte[(int) entry.getSize()];
                sevenZFile.read(content, 0, content.length);
                out.write(content);
                out.close();
            }
            entry = sevenZFile.getNextEntry();
        }
        sevenZFile.close();
    }
}
