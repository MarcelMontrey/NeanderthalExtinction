# jmrm

## Author
Marcel Montrey (marcel.montrey@gmail.com)

## Description
A library of useful modeling tools in java.

## Classes
- *FileIO* provides an interface for loading from and writing to files.
- *Graph* generates random k-regular graphs using Bollobás' pairing model algorithm, with the option of converting graphs to a matrix representation. Steger and Wormald's algorithm is also implemented, for higher values of k where restarts become prohibitively common.
- *Parameters* allows you to easily load simulation parameters from a text file.
- *Statistics* takes copies of the same file found in multiple subdirectories and returns its means, SDs and SEs.
- *Utilities* contains useful functions related to randomization, selection, summing, array handling, etc.
