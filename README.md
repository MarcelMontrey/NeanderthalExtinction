# Description
Agent-based model comparing differential fitness versus genetic drift explanations of Neanderthal extinction, including on a 2D lattice. This code was used to generate data presented in the following journal publication:

Shultz, D. R., Montrey, M., & Shultz, T. R. (2019). Comparing fitness and drift explanations of Neanderthal replacement *Proc. R. Soc. B.* **286**: 20190907. http://doi.org/10.1098/rspb.2019.0907

The full simulation code is also available from the Dryad Digital Respoistory: https://doi.org/10.5061/dryad.5kc2sm0

# Abstract
There is a general consensus among archaeologists that replacement of Neanderthals by anatomically modern humans in Europe occurred around 40–35 ka. However, the causal mechanism for this replacement continues to be debated. Proposed models have featured either fitness advantages in favour of anatomically modern humans or invoked neutral drift under various preconditions. Searching for specific fitness advantages in the archaeological record has proven difficult, as these may be obscured, absent or subject to interpretation. To bridge this gap, we rigorously compare the system-level properties of fitness- and drift-based explanations of Neanderthal replacement. Our stochastic simulations and analytical predictions show that, although both fitness and drift can produce replacement, they present important differences in (i) required initial conditions, (ii) reliability, (iii) time to replacement, and (iv) path to replacement (population histories). These results present useful opportunities for comparison with archaeological and genetic data. We find greater agreement between the available empirical evidence and the system-level properties of replacement by differential fitness, rather than by neutral drift.

# Getting Started
1. Set your desired parameters in params.txt. If you wish to iterate over a parameter, you may enter a list of comma-separated values. For example, to run the simulation for a world of depth 1, 3, 5 and 10, enter:
	DEPTH=[1,3,5,10]

	You may also iterate using steps. To do so, enter the parameter's initial value, step size, and final value, separated by colons. For example, to run the simulation for every even world depth of 4 through 20, enter:
	DEPTH=[4:2:10]

	If you enter no step size, a step size of 1 is assumed. You may iterate two parameters at once, but these will increment in parallel.

2. On Windows, 1_clean.cmd empties out (and creates, if it does not already exist) a subfolder called /data. By default, this is where data is written.

3. On Windows, 2_run.cmd runs the simulation, iterating over the desired parameter settings and number of runs. On Linux, call "java RandomWalk2D.RandomWalk" instead.

4. On Windows, 3_stats.cmd averages results across all independent runs, for each parameter setting. It then compiles these averages across parameter settings. On Linux, call "java jmrm.Statistics -se data general.tsv" and "java jmrm.Statistics -c -se data general.tsv" instead.

5. On Windows, run4.cmd and run8.cmd will run 4 or 8 instances of the simulation in parallel. An offset parameter is passed to the simulation, to ensure there are no collisions when writing to a file. Useful for taking advantage of multicore processors.

# License
Distributed under the MIT License. See LICENSE.txt for more information.

# Contact
Marcel Montrey - marcel.montrey@gmail.com
