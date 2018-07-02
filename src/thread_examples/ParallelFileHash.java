package thread_examples;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.xml.bind.DatatypeConverter;

/**
 * Parallel computation of the cryptographic SHA-256 hashes over on all files that
 * are provided as command line arguments to this program.
 *
 * Example:
 * <code>
 * $ java ch.bfh.ti.oop2.threads.ParallelFileHash ideaIC-2018.1.3.dmg jdk-10.0.1_osx-x64_bin.dmg \
 *                                        ubuntu-18.04-live-server-amd64.iso
 * </code>
 *
 * Output:
 * <code>
 * 2831417cdf987638b236ddaee6acc2e0621c5b7d460d78dfbdb162fa6c8a855c  ideaIC-2018.1.3.dmg
 * cf3d33be870788eed5bb5eeef8f52aa9d7601955c8742efbec0cf9fbd6245ceb  jdk-10.0.1_osx-x64_bin.dmg
 * 7a1c2966f82268c14560386fbc467d58c3fbd2793f3b1f657baee609b80d39a8  ubuntu-18.04-live-server-amd64.iso
 * </code>
 *
 * This output can be piped to a file and then used by the `sha256sum` tool to verify
 * the integrity of the files.
 * <code>
 *   $ java ch.bfh.ti.oop2.threads.ParallelFileHash ideaIC-2018.1.3.dmg jdk-10.0.1_osx-x64_bin.dmg \
 *                                                  ubuntu-18.04-live-server-amd64.iso  > SHA256SUMS
 *   $ sha256sum -c SHA256SUMS
 *   ideaIC-2018.1.3.dmg: OK
 *   jdk-10.0.1_osx-x64_bin.dmg: OK
 *   ubuntu-18.04-live-server-amd64.iso: OK
 * </code>
 */
public class ParallelFileHash {

    /** Number of threads to use. */
    private static final int NUM_THREADS = 8;

    /** Global array that stores the hash strings for each file */
    private static String[] hashes;


    /** Computes the SHA-256 hash for each file specified in argument args. */
    public static void main(String[] args) {
        hashes = new String[args.length];
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Compute the hashes in parallel.
        for (int i = 0; i < args.length; i++) {
            final String fileName = args[i];
            final int fileIndex = i;
            executor.execute(() -> computeFileHash(fileName, fileIndex));
        }

        executor.shutdown();
        try {
            // Wait until all tasks have been processed and the executor has
            // terminated, but at most one hour.
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // Print the hashes to stdout along with the filename.
        for (int i = 0; i < args.length; i++) {
            System.out.format("%s  %s\n", hashes[i], args[i]);
        }

    }

    /** Computes the SHA-256 hash of the specified file and stores it hashes[fileIndex]. */
    private static void computeFileHash(String fileName, int fileIndex) {
        byte[] buffer = new byte[8192];  // Temporary buffer that holds a chunk of a file.
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // get instance of SHA256 digest algorithm
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));
            int len;
            // Read file in chunks of buffer.lengths
            do {
                len = in.read(buffer, 0, buffer.length); // reads up to buffer.lengths bytes from file
                if (len > 0) {
                    md.update(buffer, 0, len);  // add the read bytes to the message digest
                }
            } while (len != -1);
            // Extract the hash
            hashes[fileIndex] = DatatypeConverter.printHexBinary(md.digest()).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            hashes[fileIndex] = e.getMessage();
        }
    }

}
