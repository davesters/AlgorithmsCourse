#!/bin/csh

# *************************************************
# config.csh
# -------------------
# This should be sourced in the profile of a csh
# or tcsh shell.
# Last edited: September 18, 2011
# *************************************************

if ( !($?ALGS4_CONFIG_SOURCED) ) then

	# Initializing variables to prevent an error
	if ( !($?CLASSPATH) ) then
		setenv CLASSPATH .
	endif
	
	# Standard libraries
	setenv CLASSPATH ${CLASSPATH}:$HOME/Projects/algorithms/algs4/stdlib.jar:$HOME/Projects/algorithms/algs4/algs4.jar
	
	# Checkstyle and Findbugs
	setenv PATH ${PATH}:$HOME/Projects/algorithms/algs4/bin
	
	# Making sure these variables aren't doubly set
	setenv ALGS4_CONFIG_SOURCED "true"
	
endif
