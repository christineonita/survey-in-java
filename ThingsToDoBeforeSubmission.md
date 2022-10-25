# Things to do before I submit #

## HW2 part B notes ##

- [ ] Remember to implement Serializable for every class except main and include their serialVersionUID
- [ ] They said yes to this: can we set a max length on the options for matching questions, something
  like 30-40 characters so formatting isn't as much of a problem?
- [ ] use SimpleDateFormatter to validate our dates
- [ ] also, validate stuff like leap years have 29 days in Feb and certain months have 31 days while others have 30
- [ ] Allow essay to allow multiple responses (short answer should allow multiple responses as well)
- [ ] Surveys don’t have correct answers
- [ ] Do not hard code file separators
- [ ] Take care of user input being alphabetical (or not an integer) when I require an integer
- [ ] Allow user to set limit (Sean said they should not exceed Integer.MAX_VALUE and throw an error
  if it does) for:
    - number of choices for multiple choice
    - short answer question length
- [ ] Do this before the stuff below this: remove methods like 'set question choices from' Question class since only
  mult choice and t/f need
  it and t/f inherits from mult choice so only override the method in t/f
- [ ] Run intelliJ code quality before i submit
- [ ] Do after copying completed files to folder i will be submitting:
    - create one question surveys for each question type then,
    - Before you submit part B make sure to provide us with a sample survey containing all types of
      questions. If you don't provide us the sample survey your submission will be returned with a 0 and
      you will be asked to fix it with a late penalty.
- [ ] Create a README indicating the types of questions each example survey has
