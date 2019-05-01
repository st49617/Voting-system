<template>
    <div>
        <v-layout justify-center>
            <v-flex lg3 md4 sm12 px-5>
                <v-layout align-space-around justify-center column fill-height px-10>
                    <v-btn large color="primary">Login</v-btn>
                    <v-btn large color="primary">Hlasování</v-btn>
                    <v-btn large color="primary">Statistiky</v-btn>
                    <v-btn large color="primary">Předchozí zastupitelstva</v-btn>
                    <v-btn large color="primary">Správa návrhů</v-btn>
                </v-layout>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
    export default {
        name: 'Menu',
        data() {
            return {}
        }
    }
</script>