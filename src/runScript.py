#!/usr/bin/env python3
import argparse
import sys
import os

parser = argparse.ArgumentParser(prog='tester')

# create the parser for the "command_1" command
parser.add_argument('--users', type=str, help='UserFile required - file with a .txt extension')
parser.add_argument('--tweets', type=str, help='TweetFile required - file with a .txt extension')
args=parser.parse_args()

if not args.users and not args.tweets:
    parser.print_help(sys.stderr)
    sys.exit(1)

javac_command = 'javac MainClass.java'
java_command = 'java MainClass ' + args.users + ' ' + args.tweets

os.system(myCmd)
os.system(myCmd)



