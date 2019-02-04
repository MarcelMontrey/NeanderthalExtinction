# Neanderthal Extinction

## Author
Marcel Montrey (marcel.montrey@gmail.com)

## Description
Model of Neanderthal extinction on a 2D lattice.

## Usage
1. Set your desired parameters in params.txt. If you wish to iterate over a parameter, you may enter a list of comma-separated values. For example, to run the simulation for a world of depth 1, 3, 5 and 10, enter:
DEPTH=[1,3,5,10]

You may also iterate using steps. To do so, enter the parameter's initial value, step size, and final value, separated by colons. For example, to run the simulation for every even world depth of 4 through 20, enter:
DEPTH=[4:2:10]

If you enter no step size, a step size of 1 is assumed. You may iterate two parameters at once, but these will increment in parallel.

2. On Windows, 1_clean.cmd empties out (and creates, if it does not already exist) a subfolder called /data. By default, this is where data is written.

3. On Windows, 2_run.cmd runs the simulation, iterating over the desired parameter settings and number of runs. On Linux, call "java RandomWalk2D.RandomWalk" instead.

4. On Windows, 3_stats.cmd averages results across all independent runs, for each parameter setting. It then compiles these averages across parameter settings. On Linux, call "java jmrm.Statistics -se data general.tsv" and "java jmrm.Statistics -c -se data general.tsv" instead.

5. On Windows, run4.cmd and run8.cmd will run 4 or 8 instances of the simulation in parallel. An offset parameter is passed to the simulation, to ensure there are no collisions when writing to a file. Useful for taking advantage of multicore processors.