# Ex1

The project passed the defined quality gate, that has the following conditions:
- New code has 0 issues;
- All new security hotspots are reviewed;
- New code is sufficiently covered by test;
- New code has limited duplication.

| Issue | Problem Description | How to solve |
| :---: | :---: | :---: |
| Security Hotspot | ``java.util.Random`` provides only pseudorandom number generator, and therefore it should not be used for security Reasons. | Use of java.security.SecureRandom |
| Code smell (major) | Invoke method(s) only conditionally | Use the ``isDebugEnabled()`` method to check if the debug level is enabled before evaluating the aerguments |
| Code smell (major) | Refactor the code in order to not assign to this loop counter from within the loop body | Put the loop increment inside the for loop definition |
| Code smell (major) | The arguments in the assertThat function are in the following order: actual, expected value | Swap 2 arguments so they are in the correct order: expected value, actual value |
| Code smell (minor) | Remove this unused import 'java.security.NoSuchAlgorithmException' | Remove the unused import |
| Code smell (minor) | It's defined ``static public EuromillionsDraw generateRandomDraw()`` | Reorder the modifiers to comply with the Java Language Specification
| Code smell (minor) | The return type of this method should be an interface such as "List" rather than the implementation "ArrayList". | It's just necessary to what it descripted
| Code smell (minor) | ``ArrayList<Dip> results = new ArrayList<Dip> ();`` | Replace the type specification in this constructor call with the diamond operator ("<>")

