<script setup>
import { ref, computed,  onMounted} from 'vue'
const pageIndexController = ref(1)
let countDown = ref(40)
const letters = ref([])
const newWord = ref('')
const wordsSubmitted = ref( [] )
const isWordValid = ref(true)
const score = ref ('');

const reloadPage = () => {
      window.location.reload();
}

const switchPage = (pageIndex) =>{
  pageIndexController.value =pageIndex
}

const checkWordValidity = computed(() =>{
  const newWordLetters = newWord.value.split('')
  // only alow more than 2-lettered words
  if (newWordLetters.length<2){
    return false
  }
  // copy without referencing it
  var assignedLetters = JSON.parse(JSON.stringify(letters.value))

  // check if the letters used are the assigned letters
  for(var i in newWordLetters){
    const letter = newWordLetters[i]
    if (!assignedLetters.includes(letter)){
      return false
    }else{
      var index = assignedLetters.indexOf(letter);
      assignedLetters.splice(index, 1);
    }
    
  }
  return true

})
const submitWord = () =>{

  fetch("https://api.dictionaryapi.dev/api/v2/entries/en/" + newWord.value)
  .then((response) => response.text())
  .then((data) => {
    const wordData  = JSON.parse(data);
    if (wordData.title == "No Definitions Found"){
      isWordValid.value = false
      newWord.value = ""
    }else{
      isWordValid.value = true
      wordsSubmitted.value.push({word: newWord.value , meaning: wordData[0].meanings[0].definitions[0].definition})
      newWord.value = ""
    }
  });
}
const submitGame = () =>{
  // Get div from last page
  switchPage(3)

  // If no words were submitted give score 0 right away
  if (wordsSubmitted.value.length ==0 ) {
    score.value = "0";
    return;
  }

  // post words to backend and obtain total score
  const wordsList = wordsSubmitted.value.map(a => a.word);
  const requestOptions = {
      method: "POST",
      body: wordsList //word1, word2
    };
    fetch("/api/scrabbleController/postScore", requestOptions)
      .then((response) => response.text())
      .then((data) => {
          score.value = data;
      });
}

const countDownTimer = () => {
      if (countDown.value > 0) {
        setTimeout(() => {
          countDown.value -= 1
          countDownTimer()
        }, 1000)
      }else{
        submitGame()
      }
    }

onMounted( ()=>{
  // executed when page is loaded
  fetch("/api/scrabbleController/getLetters")
  .then((response) => response.text())
  .then((data) => {
    letters.value = JSON.parse(data);
  });
  
})

</script>

<template>
  <h1>Solitaire Scrabble</h1>

  <div v-if="pageIndexController == 1">
  <h2>Welcome!</h2>
  <u>Rules:</u>
  <p>Use only the letters that were given to you</p>
  <p>Write words in English</p>
  <p>Submit as many words as you can before you run out time</p>
  <p>The score will be calculated after the game finished</p>
  <p>Good Luck!</p>
  <button class="btn btn-primary" @click="switchPage(2);countDownTimer()">Start Game</button>
  <br><br>
  </div>


  <div v-if="pageIndexController == 2">
  <!-- Clock -->
  <div style="border: solid; border-radius: 50%; padding: 15px ;  display: inline-block;">{{countDown}}</div>
  <br><br>

  <!-- Assigned Letters -->
  <div v-for="n in (letters.length)" class="boxLetter" :key="n">{{ letters[n - 1] }}</div>
  <br><br>

  <!-- Form to submit words -->
  <form class="add-word" 
  @submit.prevent="submitWord"
  >
    <p>Use only the above letters</p>
    <input v-model.trim="newWord" type="text" placeholder="Write here">
    <button :disabled="!checkWordValidity" class="btn btn-primary">Submit word</button>
    <p v-if="!isWordValid" style="color:red" >Invalid Word!</p>    
  </form>

  <!-- List of words that were submitted -->
  <p>Words Submitted:</p>
  <ul>
    <li v-for="({word, meaning}, index) in wordsSubmitted" :key="index">
      [word: {{ word }}, meaning: {{ meaning }} ] 
    </li>
  </ul>
  </div>


  <div v-if="pageIndexController == 3">
    <h1>Game Over</h1>
    <h2>Your score is {{score}}</h2>
    <button class="btn btn-primary" @click="reloadPage">New Game</button>
  </div>
  

</template>



<style>
.boxLetter {
  border: solid cadetblue;
  /* padding: 15px 10px 10px ; */
  padding: 15px;
  display: inline-block;
  font-size: xx-large;
  width: 20px;
}

.btn {
  border: none;
  border-radius: 3px;
  margin: auto 0;
  padding: 0.5rem 0.75rem;
  flex-shrink: 0;
  cursor: pointer;
  font-size: 0.9rem;
  letter-spacing: 0.5px;
  transition: all 0.1s ease-in;
}

.btn[disabled] {
  background: #8795a1;
}

.btn[disabled]:hover {
  background: #606f7b;
}

.btn-primary {
  background: #6cb2eb;
  color: #fff;
}

.btn-primary:hover {
  background: #3490dc;
}


.btn-cancel {
  background: #ef5753;
  color: #fff;
}

.btn-cancel:hover {
  background: #e3342f;
  color: #fff;
}

</style>

