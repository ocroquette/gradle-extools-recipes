class ExtoolConfWriter {
    static void writeExtoolConf(File directory, lines) {
        new File(directory, "extools.conf").text = lines.join("\n")
    }
}
